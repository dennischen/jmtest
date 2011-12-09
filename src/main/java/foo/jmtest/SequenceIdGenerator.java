package foo.jmtest;

import javax.servlet.http.HttpServletRequest;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.IdGenerator;

public class SequenceIdGenerator implements IdGenerator{
	public String nextComponentUuid(Desktop desktop, Component comp) {
		String number;
		if ((number = (String)desktop.getAttribute("Id_Num")) == null) {
			number = "0";
			desktop.setAttribute("Id_Num", number);
		}
		int i = Integer.parseInt(number);
		i++;// Start from 1
		desktop.setAttribute("Id_Num", String.valueOf(i));
		return "t_" + i;
	}

	public String nextDesktopId(Desktop desktop) {
		HttpServletRequest req = (HttpServletRequest)Executions.getCurrent().getNativeRequest();
		String dtid = req.getParameter("tdtid");
		if(dtid!=null){
//			System.out.println(" use client dtid "+dtid);
		}
		return dtid==null?null:dtid;
	}
	public String nextPageUuid(Page page) {
		// TODO Auto-generated method stub
		return null;
	}
}
