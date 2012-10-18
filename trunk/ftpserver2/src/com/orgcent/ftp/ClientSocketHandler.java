package com.orgcent.ftp;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientSocketHandler extends IoHandlerAdapter {
	private static Logger log = LoggerFactory.getLogger("OAM");

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		String m = cause.getMessage();
		if (m == null || !m.equalsIgnoreCase("远程主机强迫关闭了一个现有的连接。")) {
			log.warn("OAM Socket exceptionCaught, IP:{}",
					session.getRemoteAddress());
			log.warn("OAM Socket exceptionCaught, close the socket", cause);
		}
		session.close(true);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {

	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("OAM Socket is connect! IP:{}",
					session.getRemoteAddress());
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("OAM Socket is disconnect! IP:{}",
					session.getRemoteAddress());
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
	}
}
