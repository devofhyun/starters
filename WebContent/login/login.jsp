<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css">
<!-- 폰트 -->
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">

<%@include file = "../nav.jsp" %>
<link rel="stylesheet" href="assets/css/login_out/login.css" />
<title>스타터스 : 로그인</title>


<div class="login_container form">
	<div class="row">
		<div class="col-md-12 h2m">
			<h2>로그인</h2>
		</div>
	</div>
	<%--<form class="form-horizontal" method="post"
		action="starters?cmd=loginAction"> --%>
		<div class="row">
			<div class="col-md-8 inputbb">
				<div class="form-group has-danger">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
						<input type="text" name="id" class="form-control" id="id" placeholder="아이디" required autofocus/>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 inputbb">
				<div class="form-group">
					<div class="input-group mb-2 mr-sm-2 mb-sm-0">
						<input type="password" name="pw" class="form-control" id="password" placeholder="비밀번호"/>
					</div>
				</div>
			</div>
		</div>
		 <div class="row">
                <div class="col-md-8 inputbb">
                <div class="btn-group btn-group-sm">
                	<button type="submit" class="btn btn-outline-secondary col-md-6" onclick = "login();">로그인</button>
                    <button type="button" class="btn btn-outline-secondary col-md-6 registerCateg" onclick = "register()">회원가입</button>
					
				</div>
					<!-- <div class="form-control-feedback">
						<button type="submit" class="btn btn-success">로그인</button>
                    	<button type="button" class="btn btn-success registerCateg" onclick = "register()">회원가입</button>
					</div> -->
				</div>
            </div>
		<div class="row">
                <div class="col-md-8 inputbb">
                <a href="starters?cmd=findId" class="findId">아이디/비밀번호 찾기</a>
                </div>
		</div>
            
<%-- 		 <div class="row">
                <div class="col-md-8 pull-right">
                    <div class="form-check">
                    	<button type="button" class="btn btn-success registerCateg" onclick = "register()">회원가입</button>
                        <button type="button" class="btn btn-success findId" onclick = "find()">아이디/비밀번호 찾기</button>
                       
                        <a href="starters?cmd=findId" class="findId">아이디/비밀번호 찾기</a>
                    </div>
                </div>
            </div> --%>
            
	<%-- </form> --%>
</div>

<%@include file = "../footer.jsp" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

<script type="text/javascript">
	//회원가입
	var register = function() {
		location.href = "starters?cmd=registerCateg";
	};
	
	//아이디 비번찾기
	var find = function() {
		location.href = "starters?cmd=findId";
	};
	
	// 로그인
	function login(){
	var userID = $('#id').val();
	var userPasswd = $('#password').val();
	$.ajax({
		type:"POST",
		url: 'starters?cmd=loginAction',
		data:{userID :userID, userPasswd : userPasswd},
		success: function(data){
			//console.log("data값///"+data.trim());
			 if(data) {
          		 data = data.trim();
          	 }
			 if(data !== "") {
                 window.location.href = "starters";
              } else {
                 alert("아이디나 비밀번호를 다시 확인해주세요.");
                 $("#id").val("");
                 $("#password").val("");
             }
		}		
	});
}
	
	</script>
