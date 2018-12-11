package org.devframe.web.common.xss;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import com.nhncorp.lucy.security.xss.XssFilter;

public class RequestWrapper extends HttpServletRequestWrapper {

	private byte[] b;

	//1. JSON 문자열 파라미터를 이스케이프하고 바이트로 만들어 놓는다.
	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		XssFilter filter = XssFilter.getInstance("lucy-xss-sax.xml");

		String bodyStr = getBody(request);

		//Json 문자열 파라미터 => {"nm":"<1","ctx":"2"}
		//Json 객체 파라미터 => nm=%3C1&ctx=2
		System.out.println("bodyStr1 : " + bodyStr);

		bodyStr = new String(filter.doFilter(bodyStr));

		//Json 문자열 파라미터 => {"nm":"&lt;1","ctx":"2"}
		//Json 객체 파라미터 => nm=%3C1&ctx=2
		System.out.println("bodyStr2 : " + bodyStr);

		b = bodyStr.getBytes();
	}

	//2. 파라미터값을 하나하나 수집할 때 사용된다.
	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bis = new ByteArrayInputStream(b);

		return new ServletInputStreamImpl(bis);
	}

 	private class ServletInputStreamImpl extends ServletInputStream {
 		private InputStream is;

 		public ServletInputStreamImpl(InputStream bis) {
 			is = bis;
 		}

		@Override
 		public int read() throws IOException {
 			return is.read();
 		}

		@Override
 		public int read(byte[] b) throws IOException {
 			return is.read(b);
 		}

		@Override
		public boolean isFinished() {
			return false;
		}

		@Override
		public boolean isReady() {
			return false;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
		}
 	}

 	public static String getBody(HttpServletRequest request) throws IOException {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();

			if(inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;

				while((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch(IOException ex) {
			throw ex;
		} finally {
			if(bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch(IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();

		return body;
 	}

}
