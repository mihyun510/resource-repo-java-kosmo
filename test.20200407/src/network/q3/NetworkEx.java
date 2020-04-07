package network.q3;

import java.net.URL;

class NetworkEx {
	  public static void main(String args[]) throws Exception {
	    URL url = new URL("http://www.ikosmo.co.kr/gallery/boardList.ans?bdTypeCd=19");
	    System.out.println("url.getAuthority():"+ url.getAuthority());
	    System.out.println("url.getPort():"+ url.getPort());
	    System.out.println("url.getFile():"+ url.getFile());
	    System.out.println("url.getHost():"+ url.getHost());
	    System.out.println("url.getPath():"+ url.getPath());
	    System.out.println("url.getProtocol():"+ url.getProtocol());
	    System.out.println("url.getQuery():"+ url.getQuery());
	    System.out.println("url.toURI():"+ url.toURI());    
	  }
	}