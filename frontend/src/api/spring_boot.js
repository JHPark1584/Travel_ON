const HOST = "https://7d0c97ac-6236-4b8f-9ba8-f257edcdfe2d.mock.pstmn.io/";

const USER = "user/";
// const QNA = "qna/";
// const NOTICE = "notice/";
// const PLAN = "plan/";
// const ALARM = "alarm/";
// const VIDEOCHAT = "videochat/";

/* eslint-disable */
export default {
  accounts: {
    login: () => HOST + USER + "login/", // 로그인
    logout: () => HOST + USER + "logout/", // 로그아웃
    regist: () => HOST + USER + "regist/", // 회원가입
    idCheck: () => HOST + USER + "idcheck/", // id 중복검사
    nickCheck: () => HOST + USER + "nickcheck/", // 닉네임 중복검사
    userInfo: (id) => HOST + USER + "detail/" + String(id) + "/", // 회원정보 조회
    userModify: () => HOST + USER + "modify/", // 회원정보 수정
    userDelete: (id) => HOST + USER + "delete/" + String(id) + "/", // 회원정보 삭제
    userPassInit: () => HOST + USER + "email/", // 비밀번호 찾기(초기화)
    userTitle: () => HOST + USER + "title/", // 칭호 변경, 불러오기
    userTrophy: () => HOST + USER + "trophy/" + String(id) + "/", // 업적지도용 여행횟수 가져오기
    userTrophyUpdate: () => HOST + USER + "trophy/", // 여행횟수 업데이트
  }
}