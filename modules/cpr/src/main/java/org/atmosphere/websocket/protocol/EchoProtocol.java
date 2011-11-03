/*
* Copyright 2011 Jeanfrancois Arcand
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not
* use this file except in compliance with the License. You may obtain a copy of
* the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations under
* the License.
*/
package org.atmosphere.websocket.protocol;

import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.websocket.WebSocketProcessor;
import org.atmosphere.websocket.WebSocket;
import org.atmosphere.websocket.WebSocketProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Simple {@link org.atmosphere.websocket.WebSocketProcessor} that invoke the {@link org.atmosphere.cpr.Broadcaster#broadcast} API when a WebSocket message
 * is received.
 *
 * NOTE: If WebSocket frame are used the bytes will be decoded into a String, which reduce performance.
 *
 * @author Jeanfrancois Arcand
 */
public class EchoProtocol implements WebSocketProtocol {
    private static final Logger logger = LoggerFactory.getLogger(AtmosphereServlet.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpServletRequest onMessage(AtmosphereResource resource, String data) {
        logger.trace("broadcast String");
        resource.getBroadcaster().broadcast(data);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpServletRequest onMessage(AtmosphereResource resource, byte[] data, int offset, int length) {
        logger.trace("broadcast byte");
        byte[] b = new byte[length];
        System.arraycopy(data, offset, b, 0, length);
        resource.getBroadcaster().broadcast(b);
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void configure(AtmosphereServlet.AtmosphereConfig config) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onOpen(AtmosphereResource<HttpServletRequest, HttpServletResponse> resource) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClose(AtmosphereResource<HttpServletRequest, HttpServletResponse> resource) {
    }
}
