package cn.cem.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.cem.Bean.Weibo;
import cn.cem.Dao.WeiboDao;
import cn.cem.Util.JsonUtil;

public class GetResultServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int PAGE_NUM = 10;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonData = null;
		resp.setContentType("application/json;charset=utf-8");	 
		int pageNum = Integer.parseInt(req.getParameter("page"));
		List<Weibo> weibos=WeiboDao.GetResult();
		boolean flag=true;
		PrintWriter pw=resp.getWriter();
		
		try {
			jsonData=JsonUtil.getJSONString(weibos);
			 pw.println("{\"jsonData\":" + jsonData +","
						+ "\"pageSize\":\"" + PAGE_NUM + "\"," + "\"pageNo\":\"" + pageNum + "\"}");
		} catch (Exception e) {
			flag=false;
			jsonData = "error:调用数据库失败";
			pw.println("{\"jsonData\":\"" + jsonData + "\",\"success\":" + flag + "}");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
