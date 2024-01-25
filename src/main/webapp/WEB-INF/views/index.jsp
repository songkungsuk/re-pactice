<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안녕하세요</title>
</head>
<body>
	오랜만입니다.
	<table border="1">
		<tr>
			<td>작성자</td>
			<td>채팅내용</td>
		</tr>
		<tbody id="chat-list">
		
		</tbody>
	</table>
	<script>
		window.addEventListener('load', function() {
			const xhr = new XMLHttpRequest();
			xhr.open('GET', '/test');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						const objs = JSON.parse(xhr.responseText);
						console.log(objs);
						let html = '';
						for(obj of objs){
							html += '<tr>';
							html += '<td>'+ obj.uiId +'</td>';
							html += '<td>'+ obj.ctContent +'</td>';
							html += '<tr>';
						}
						document.querySelector('#chat-list').innerHTML = html; 	
					}
					
				}
			}
			xhr.send();
		})
	</script>

</body>
</html>