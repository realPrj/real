<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공조 || 강의계획서 등록 페이지</title>
</head>
<script>
	//form 생성
	function createForm(formname, formaction, formmethod) {

		var form = document.createElement("form");

		form.name = formname;
		form.action = formaction;
		form.method = formmethod;

		document.body.appendChild(form);

	}

	function planinert() {

		var boardCode = document.getElementsByName("boardCode")[0];
		var boardContent = document.getElementsByName("boardContent")[0];
		var boardTitle = document.getElementsByName("boardTitle")[0];

		createForm("learningPlanInsertform", "learningPlanInsert", "post");

		var form = document.getElementsByName("learningPlanInsertform")[0];

		form.appendChild(boardCode);
		form.appendChild(boardContent);
		form.appendChild(boardTitle);

		form.submit();

		$("input[name = boardCode]").remove();
	}
</script>
<body>
	<input type="hidden" value="${boardcode }" name="boardCode" />
	<div style="width: '50px'">
		<form name="fileForm" action="learningPlanInsert" method="post"
			enctype="multipart/form-data">
			<table border="2" width="100%" height="300px">
				
				<thead class="table table-striped table-bordered">
					<tr>
						<th>제목</th>
						<td><input type="text" size="50%" name="boardTitle"
							class="form-control" style="margin-left: 50px"></td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="2"><textarea class="form-control" cols="71"
								rows="20" name="boardContent" style="margin-left: 70px"></textarea></td>
					</tr>
					<tr>
						<th style="padding-top: 15px">첨부파일</th>
						<td><input type="file" class="btn btn-default" multiple="multiple" name="file"><input type="hidden"
							name="load" value="Notice" /></td>

					</tr>

				</tbody>
				<input type="hidden" value="${boardcode }" name="boardCode" />
			
			</table>
			<input type="submit" value="등록" style="width: 50pt; height: 30pt;margin-left: 300px" />
			<div align="center"></div>
		</form>



	</div>
</body>
</html>