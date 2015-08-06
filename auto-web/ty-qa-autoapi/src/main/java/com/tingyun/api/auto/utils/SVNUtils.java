package com.tingyun.api.auto.utils;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

/**
* @author :chenjingli 
* @version ：2015-8-5 下午2:54:45 
* @decription: svn code check out
 */
public class SVNUtils {
	
	private static Logger LOG = LoggerFactory.getLogger(SVNUtils.class);
	
	private static String SVN_URL=null;
	
	private static  String SVN_USERNAME=null;
	
	private static  String SVN_PWD=null;
	//副本本地路径
	private static  String LOCAL_SVN_WORKSPACE=null;
	//生命svn客户端
	private static SVNClientManager ourClientManager;
	//cong svn客户端 manager 获得 SVNUpdateClient updateClient
	private static SVNUpdateClient updateClient;
	//本地副本代码路径
	private static File filedir = null;
	static{
		SVN_URL="https://192.168.1.42/nbs-automation-test/trunk/auto-web/ty-qa-autoapi";
		SVN_USERNAME="chenjl";
		SVN_PWD="chenjl@123";
		LOCAL_SVN_WORKSPACE="F:local_svn_workspace";
		//创建本地代码副本路径
		filedir = new File(LOCAL_SVN_WORKSPACE);
		//版本初始化
        DAVRepositoryFactory.setup();
		//加载驱动选项
		ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
		//创建svn manager实例提供认证信息（用户名，密码）和驱动选项。
		ourClientManager = SVNClientManager.
					newInstance((DefaultSVNOptions)options,SVN_USERNAME,SVN_PWD);
		/*
         * 递归的把工作副本从repositoryURL check out 到 wcDir目录。
         * SVNRevision.HEAD 意味着把最新的版本checked out出来。  通过svn客户端
         */
		updateClient = ourClientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);	
	}
	
	/*第六步：
     * 通过SVNClientManager的实例获取要进行操作的client实例（如             *  SVNUpdateClient）
     * 通过client实例来执行相关的操作。
	 * 此框架以check out操作来进行说明，其他操作类似。
     */
	public static void checkCodeFromSvn(){
		
		if (filedir.exists()) {
			LOG.warn("您本地已存在相同路径：{}即将做更新操作....",filedir.getAbsolutePath());
			SVNUtils.updateCodeFromSvn();
		}else{
			filedir.mkdir();
		}
		//调用do checkoout方法 下载代码
		try {
			updateClient.doCheckout(SVNURL.parseURIDecoded(SVN_URL),filedir,SVNRevision.HEAD, SVNRevision.HEAD, true);
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			LOG.error("checkout code from svn to local exception {}",e);
		}
		LOG.info("successful from svn check code!");
	}
	/**
	* @author : chenjingli
	* @decription  svn的更新操作
	 */
	
	public static void updateCodeFromSvn(){
		LOG.info("start update code form svn !!!");
		//调用do checkoout方法 下载代码
		try {
			updateClient.doUpdate(filedir, SVNRevision.HEAD, SVNDepth.INFINITY,false,false);
		} catch (SVNException e) {
			// TODO Auto-generated catch block
			LOG.error("update code from svn to local exception {}",e);
		}
		LOG.info("successful from svn update code!!!");
	}
	public static void main(String[] args) {
		SVNUtils.checkCodeFromSvn();
	}
}
