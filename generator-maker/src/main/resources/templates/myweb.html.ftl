<!DOCTYPE html>
<html>
<head>
	<title>网页</title>
</head>
<body>
	<h1>网页</h1>
	<p>这是我的网页。</p>
	<p>欢迎访问！</p>
    <ul>
       <#--  循环渲染导航条 -->
       <#list menuItems as item>
          <li><a href="${item.url}">${item.label}</a></li>

       </#list>
    </ul>
   <footer>
       ${currentYear} test网络
   </footer>
</body>
</html>