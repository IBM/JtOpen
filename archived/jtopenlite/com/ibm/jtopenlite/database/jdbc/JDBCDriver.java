///////////////////////////////////////////////////////////////////////////////
//
// JTOpenLite
//
// Filename:  JDBCDriver.java
//
// The source code contained herein is licensed under the IBM Public License
// Version 1.0, which has been approved by the Open Source Initiative.
// Copyright (C) 2011-2012 International Business Machines Corporation and
// others.  All rights reserved.
//
///////////////////////////////////////////////////////////////////////////////

package com.ibm.jtopenlite.database.jdbc;

import java.sql.*;
import java.util.*;

import com.ibm.jtopenlite.Trace;

/**
A JDBC 3.0 driver that accesses DB2 for IBM i databases.

<p>The jtopenlite driver is designed to provide a JDBC driver
suitable for use on mobile devices.  Because of this, the following
features are not supported.

<ul>
<li>No JDBC 4.0 support
</li>
<li>No ResultSet positioning (other than rs.next())
</li>
<li>No output parameter support for stored procedure calls
</li>
<li>No lob support
</li>
<li>No XML support
</li>
<li>Minimal connection properties -- i.e. Only SQL naming, no translate binary, ...
</li>
<li>No SQLArray support
</li>
<li>No JDBC escape syntax support
</li>
<li>No prepared statement batching
</li>
<li>No PreparedStatement metadata
</li>
<li>No Autogenerated keys support
</li>
<li>No support for MIXED ccsids
</li>
<li>No support for double byte CCSIDs (except for unicode 1200 and 13488)
</li>
<li>No support for BIDI ccsids
</li>
<li>No support for multiple result sets from stored procedures
</li>


</ul>

<p>To use this driver, the application or caller must register
the driver with the JDBC DriverManager.  This class also registers
itself automatically when it is loaded.

<p>After registering the driver, applications make connection
requests to the DriverManager, which dispatches them to the
appropriate driver.  This driver accepts connection requests
for databases specified by the URLs that match the following syntax:

<pre>
jdbc:jtopenlite://<em>system-name</em>;PROPERTIES
</pre>

<p>The driver uses the specified system name to connect
to a corresponding IBM i system.

<p>Only the following properties are supported
<table summary="">
<tr>
<th>Property</th>
<th>Description</th>
<th>Choices</th>
<th>Default</th>
</tr>
<tr>
<td>user</td>
<td>Specifies the user name for connecting to the server.</td>
<td>server user</td>
<td>none</td>
</tr>
<tr>
<td>password</td>
<td>Specifies the password for connecting to the server</td>
<td>system password</td>
<td>none</td>
</tr>
<tr>
<td>secure</td>
<td>Specifies whether a Secure Sockets Layer (SSL) connection is used to communicate with the server</td>
<td>
<ul>
<li>"true" (encrypt all client/serve communication)</li>
<li>"false" (encrypt only the password)</li>
</ul>
</td>
<td>"false"</td>
</tr>
<tr>
<td>debug</td>
<td>Specifies whether debug information should be recorded</td>
<td>
<ul>
<li>"true" (debug information is recorded)</li>
<li>"false" (no debug information is recorded)</li>
</ul>
</td>
<td>"false"</td>
</tr>
</table>

<p>The following example URL specifies a connection to the
database on system <em>mysystem.helloworld.com</em>.
<pre>
jdbc:jtopenlite://mysystem.helloworld.com
</pre>

<p> The following is a simple JDBC program
<pre>
import java.sql.*;
import java.io.*;
public class RunSql {
   public final static String PROMPT="ENTER SQL STATEMENT or exit &gt; ";
   public static void main(String[] args) {
	   try {
		   Class.forName("com.ibm.jtopenlite.database.jdbc.JDBCDriver");
		   String url = args[0];
		   String userid = args[1];
		   String password = args[2];
		   Connection connection = DriverManager.getConnection(url, userid, password);
		   Statement statement = connection.createStatement();
		   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		   System.out.print(PROMPT);
		   String line = reader.readLine().trim();
		   while (! line.equalsIgnoreCase("exit") ) {
			   try {
				   boolean results = statement.execute(line);
				   if (results) {
					   ResultSet rs = statement.getResultSet();
					   ResultSetMetaData rsmd = rs.getMetaData();
					   int columnCount = rsmd.getColumnCount();
					   StringBuffer sb = new StringBuffer();
					   while (rs.next()) {
						  sb.setLength(0);
						  sb.append(rs.getString(1));
						  for (int column = 2; column &lt;= columnCount; column++) {
							  sb.append(",");
							  sb.append(rs.getString(column));
						  }
						  System.out.println(sb.toString());
					   }
				   }
			   } catch (SQLException sqlex) {
				   System.out.println("SQLException caught");
				   System.out.println(sqlex.toString());
			   }
			   System.out.print(PROMPT);
			   line = reader.readLine().trim();
		   }

	   } catch (Exception e) {
		   System.out.println("Fatal error occurred");
	     e.printStackTrace(System.out);
	     System.out.println("Usage: java com.ibm.jtopenlite.samples.RunSql JDBCURL USERID PASSWORD");

	   }
   }

}

</pre>
 *
 */
public class JDBCDriver implements Driver
{
  public static final String DATABASE_PRODUCT_NAME_ = "DB2 UDB for AS/400";
  public static final String DRIVER_NAME_ =           "jtopenlite JDBC Driver";
  public static final String DRIVER_LEVEL_ =          "01.00";
  public static final int MAJOR_VERSION_ = 1;
  public static final int MINOR_VERSION_ = 0;
  public static final int JDBC_MAJOR_VERSION_ = 3;
  public static final int JDBC_MINOR_VERSION_ = 0;
  public static final String URL_PREFIX_ =  "jdbc:jtopenlite://";

  // public static boolean globalDebugOn = false;

static
  {
	// String debugProperty = System.getProperty("com.ibm.jtopenlite.Trace.category");
	//if (debugProperty != null) {
	//	debugProperty = debugProperty.toLowerCase();
	//	if (debugProperty.equals("jdbc") || debugProperty.equals("all") || debugProperty.equals("true")) {
	//		globalDebugOn = true;
	//	}
	//}
    try
    {
      DriverManager.registerDriver(new JDBCDriver());
    }
    catch (SQLException sql)
    {
      throw new RuntimeException("Error registering driver: "+sql.toString(), sql);
    }
  }

  public JDBCDriver()
  {
  }

  public boolean acceptsURL(String url) throws SQLException
  {
      if (url == null) return false;
      return url.startsWith(URL_PREFIX_);
  }

  public Connection connect(String url, Properties info) throws SQLException
  {
    if (acceptsURL(url))
    {
      String system = url.substring(18);
      int semi = system.indexOf(";");
      if (semi >= 0)
      {
	if (info == null) info = new Properties();
        addURLProperties(system.substring(semi+1), info);
      }
      int slash = system.indexOf("/");
      if (semi >= 0 || slash >= 0)
      {
        int min = semi >= 0 && slash >= 0 ? (semi < slash ? semi : slash) : (semi >= 0 ? semi : slash);
        system = system.substring(0, min);
      }
      system = system.trim();
      String user = info.getProperty("user");
      String password = info.getProperty("password");
      boolean isSSL = Boolean.parseBoolean(info.getProperty("secure","false"));
      boolean debugOn = info.getProperty("debug", "false").equals("true");
      if (Trace.isStreamTracingEnabled()) {
    	  debugOn=true;
      }
      return JDBCConnection.getConnection(isSSL, system, user, password, debugOn);
    }
    return null;
  }

  private static final void addURLProperties(String url, Properties info)
  {

    StringTokenizer st = new StringTokenizer(url, ";");
    while (st.hasMoreTokens())
    {
      String tok = st.nextToken();
      int eq = tok.indexOf("=");
      if (eq >= 0)
      {
        info.setProperty(tok.substring(0,eq), tok.substring(eq+1));
      }
    }
  }

  public int getMajorVersion()
  {
    return 1;
  }

  public int getMinorVersion()
  {
    return 0;
  }

  public DriverPropertyInfo[] getPropertyInfo(String url, Properties info)
  {
    if (info == null) info = new Properties();
    return new DriverPropertyInfo[]
    {
      new DriverPropertyInfo("debug", info.getProperty("debug", "false"))
    };
  }

  public boolean jdbcCompliant()
  {
    return false;
  }

  public String   toString() {
	  return DRIVER_NAME_;
  }
}
