package com.tingyun.api.auto.utils.fifter;


/**
* @author :chenjingli 
* @version ：2015-8-1 下午8:47:32 
* @decription:事务处理拦截器
 */
public class TransactionFilter{
	/**
	 * 
	
	static Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		Connection connection = null;
				try {
					connection = DBUtils.getConnection();
					//开启事务
					connection.setAutoCommit(false);
					//利用thredlocal 绑定当前connection
					ConnectionContext.getInstance().bind(connection);
					//请求转发到servlet
					chain.doFilter(request, response);
					//提交事务
					connection.commit();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						connection.rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 HttpServletRequest req = (HttpServletRequest) request;
					 HttpServletResponse res = (HttpServletResponse) response;
					 //req.setAttribute("errMsg", e.getMessage());
					 //req.getRequestDispatcher("/error.jsp").forward(req, res);
					 //出现异常之后跳转到错误页面
					 res.sendRedirect("error.jsp");
				}finally{
					ConnectionContext.getInstance().remove();
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	 */
}
