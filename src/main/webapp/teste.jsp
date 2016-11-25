<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>

	<script type="text/javascript">
		function post(path, params, method) {
			method = method || "post"; // Set method to post by default if not specified.

			// The rest of this code assumes you are not using a library.
			// It can be made less wordy if you use one.
			var form = document.createElement("form");
			form.setAttribute("method", method);
			form.setAttribute("action", path);

			for ( var key in params) {
				if (params.hasOwnProperty(key)) {
					var hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", key);
					hiddenField.setAttribute("value", params[key]);

					form.appendChild(hiddenField);
				}
			}

			document.body.appendChild(form);
			form.submit();
		}

		function pegarID() {
			//post('/contact/', {name: 'Johnny Bravo'});

			post('#', {
				idsistema : document.getElementById("mySelect").selectedIndex
			}, 'get');

		}
	</script>

	<select id="mySelect">
		<option value="void" id="1" onclick="pegarID();">Choose your
			answer</option>
		<option value="To measure time" id="2" onclick="pegarID();">To
			measure time</option>

	</select>

</body>
</html>