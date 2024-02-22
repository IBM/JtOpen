package com.ibm.as400.access;

import java.io.IOException;
import java.io.OutputStream;

public class HCSRouteNewConnDS extends ClientAccessDataStream
{
  private static final String copyright = "Copyright (C) 1997-2001 International Business Machines Corporation and others.";

    public HCSRouteNewConnDS(byte[] _connReqID)
    {
      super(new byte[84]);
      setLength(84);
      data_[4] = 0x00;
      setServerID(0xE00B);
      setTemplateLen(64);
      setReqRepID(0x7107);
      
      System.arraycopy(_connReqID, 0, data_, 20, 64);
    }

    void write(OutputStream out) throws IOException
    {
      if (Trace.traceOn_) Trace.log(Trace.DIAGNOSTIC, "Sending route new connection request..."); 
      super.write(out);
    }
}
