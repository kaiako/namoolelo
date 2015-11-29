package com.namoolelo.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.namoolelo.web.model.Envelope;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JsonResponseFilter implements Filter{

	protected static final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		ResponseWrapper responseWrapper = new ResponseWrapper(response);
		log.debug("Before doFilter");
		chain.doFilter(request, responseWrapper);
		
		String contentType = responseWrapper.getContentType();
		if(isNoCacheResponse(contentType) && contentType.matches(MediaType.APPLICATION_JSON_VALUE+".*")){
			log.debug("Content type is application/json");
			
			String content = new String(responseWrapper.getDataStream(), "UTF-8");
			
			try{
				Envelope envelope = mapper.readValue(content, Envelope.class);
				response.getOutputStream().write(mapper.writeValueAsBytes(envelope));
				response.setStatus(envelope.getCode());
			} catch (JsonMappingException exception){
				log.debug("not an envelope json call.");
				response.getOutputStream().write(responseWrapper.getDataStream());
			}
		} else {
			response.getOutputStream().write(responseWrapper.getDataStream());
		}
		
		
	}

	@Override
	public void destroy() {	}

	private boolean isNoCacheResponse(String contentType) {
		if(StringUtils.isNotBlank(contentType) && contentType.matches(MediaType.APPLICATION_JSON_VALUE+".*")){
			return true;
		}
		return false;
	}
	
	public static void setNoCacheResponse(HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		response.setHeader("Pragma",  "no-cache");
		response.setDateHeader("Expires", 0);
	}
}
