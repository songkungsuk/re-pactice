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

	<input type="text" placeholder="채팅내용" id="ctContent">
	<button onclick="insertChat()">채팅보내기</button>
	<br>
	<button onclick="updateChat()">수정하기</button>
	<br>
	<button onclick="deleteChat()">삭제하기</button>

	<script>
		let url = new URL(location.href);
		let params = url.searchParams;
		const ctNum = params.get("ctNum");

		window
				.addEventListener(
						'load',
						function() {

							console.log(ctNum);

							const xhr = new XMLHttpRequest();
							xhr.open('GET', '/test/view-data?ctNum=' + ctNum);
							xhr.onreadystatechange = function() {
								if (xhr.readyState == 4) {
									if (xhr.status == 200) {
										const obj = JSON
												.parse(xhr.responseText);

										let html = '';
										html += '<tr>';
										html += '<td>' + obj.uiId + '</td>';
										html += '<td><input type="text" id="ctinfo" value="'+ obj.ctContent+'"/>'
												+ '</td>';
										html += '<tr>';

										document.querySelector('#chat-list').innerHTML = html;
									}

								}
							}
							xhr.send();
						})

		function insertChat() {
			const param = {
				uiNum : 9,
				ctContent : document.querySelector('#ctContent').value,
				ctRoomnum : 1
			}
			console.log(param);
			const xhr = new XMLHttpRequest();
			xhr.open('POST', '/test/insert')
			xhr.setRequestHeader('Content-Type', 'application/json;');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						console.log(xhr.responseText);
					}
				}
			}
			xhr.send(JSON.stringify(param));

		}

		function updateChat() {
			const param = {
				ctNum : ctNum,
				ctContent : document.querySelector('#ctinfo').value,
			}
			console.log(param);
			const xhr = new XMLHttpRequest();
			xhr.open('POST', '/test/update')
			xhr.setRequestHeader('Content-Type', 'application/json;');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						console.log(xhr.responseText);
					}
				}
			}
			xhr.send(JSON.stringify(param));

		}

		function deleteChat() {
			const param = {
				ctNum : ctNum
			}
			console.log(param);
			const xhr = new XMLHttpRequest();
			xhr.open('POST', '/test/delete')
			xhr.setRequestHeader('Content-Type', 'application/json;');
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4) {
					if (xhr.status == 200) {
						if (xhr.responseText == 1) {
							alert('삭제완료');
							location.href = "/test/list";
						}

					}
				}
			}
			xhr.send(JSON.stringify(param));

		}
	</script>

</body>
</html>