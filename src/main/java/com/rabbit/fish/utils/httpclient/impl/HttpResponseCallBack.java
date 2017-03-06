package com.rabbit.fish.utils.httpclient.impl;

import java.io.IOException;
import java.io.InputStream;

public interface HttpResponseCallBack {
	void processResponse(InputStream responseBody) throws IOException;
}
