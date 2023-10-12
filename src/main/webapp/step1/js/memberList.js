//브라우저의 이벤트처리 3단계
//ㄱ.이벤트 객체 생성 : 이벤트와 관련된 모든 정보가 담김
//ㄴ.해당 이벤트와 연결된 이벤트핸들러를 자동으로 호출함
//ㄷ.호출한 이벤트 핸들러의 매개변수로 처음 생성한 이벤트 객체를 넣어줌.
//event객체 안에 default이벤트를 금지하는 메서드가 있음

function submitHandler(event) {
	let passwd = document.querySelector('#passwd');
	let repasswd = document.querySelector('#repasswd');
	let name = document.querySelector('#name');
	let msgDiv = document.querySelector('#msg');
	let inputCheck = true;
//	모든 사용자의 입력이 true라고 가정함
	let msg = '';
	msgDiv.innerHTML = '';
	
	if(passwd.value.length < 4) {
		msg = '비밀번호는 4글자 이상이어야 합니다!';
		passwd.value = '';
		repasswd.value = '';
		inputCheck = false;
	}
	else if(passwd.value !== repasswd.value) {
		msg = '비밀번호와 비밀번호 확인은 서로 일치해야 합니다!';
		passwd.value = '';
		repasswd.value = '';
		inputCheck = false;
	}
	else if(name.value.length < 2) {
		msg = '이름은 2글자 이상이어야 합니다.';
		name.value = '';
		inputCheck = false;
	}
	
	if(!inputCheck) {
		event.preventDefault();
		msgDiv.innerHTML = msg;
	}
}

function modifyHandler(event) {
	let id = event.target.getArribute('id');
//	target은 현재 이벤트가 발생한 그 요소 객체
	let url = 'step1Controller.jsp?command=modify&id=' + id;
	
	location.href = url;
}

function init() {
	let insertMember = document.querySelector("#insertMember");
	let listTable = document.querySelector("#listTable");

	insertMember.addEventListener('submit', submitHandler);
	listTable.addEventListener('click', modifyHandler);
}

window.addEventListener('load', init);