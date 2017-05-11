package com.ez.ftp;

import com.ez.portal.connection.ftp.factory.FTPConnectionFactory;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class Main {
    
    public void doOperation() {
        FTPConnectionFactory ftpConnectionFactory = new FTPConnectionFactory();
        Session session = ftpConnectionFactory.openSession();
        try {
            Channel channel = session.openChannel("sftp");
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
