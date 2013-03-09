package com.ego.apps.commonshare.beans.url;

/**
 * Representation of a URL.
 * 
 * @author sony
 * 
 */
public class MenuUrl
	{
	private String url;
	private String text;

	public MenuUrl()
		{
		// TODO Auto-generated constructor stub
		}

	public MenuUrl(String text, String url)
		{
		this.url = url;
		this.text = text;
		}

	public String getUrl()
		{
		return url;
		}

	public void setUrl(String url)
		{
		this.url = url;
		}

	public String getText()
		{
		return text;
		}

	public void setText(String text)
		{
		this.text = text;
		}

	}
