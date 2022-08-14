import axios from "axios";
import spring from "@/api/spring_boot";

export const Plans = {
  /* eslint-disable */
  state: () => ({
    planHistoryList: [],
    convertedHistoryList: [],
  }),
  getters: {
    planHistoryList: (state) => state.planHistoryList,
    convertedHistoryList: (state) => state.convertedHistoryList,
  },
  mutations: {
    SET_PLAN_HISTORY_LIST: (state, planHistoryList) => (state.planHistoryList = planHistoryList),
    SET_CONVERTED_HISTORY_LIST: (state, convertedHistoryList) => (state.convertedHistoryList = convertedHistoryList),
  },
  actions: {
    convertPlanList({ getters, commit }) {
        console.log("플랜 변환 시작")
        let convertedHistoryList = [];
        let tempDate = null; // 임시 저장 날짜
        let listCount = 0;
        let tempList = []; // 임시 저장 리스트
        getters.planHistoryList.forEach((element) => {
          if (element.visitDate !== tempDate) {
            // 임시 저장 날짜와 엘리먼트 날짜가 다르면
            tempList.push(element.visitDate); // 날짜 정보 임시 저장
            tempDate = element.visitDate; // 임시 저장 날짜 갱신
            listCount += 1; // 카운트 1 증가
            if (listCount === 15) {
              // 방금으로 인해 카운트가 15됬을 경우,
              listCount = 1; // 카운트 1
              convertedHistoryList.push(tempList); // 15짜리 리스트 출력 전용 리스트 push
              tempList = []; // 임시 리스트 초기화
              tempList.push(tempDate); // 임시 리스트에 날짜 정보 임시 저장
            }
          }
          tempList.push(element); // 엘리먼트 임시 저장
          listCount += 1; // 카운트 1 증가
          if (listCount === 15) {
            // 방금으로 인해 카운트가 15됬을 경우,
            convertedHistoryList.push(tempList);
            tempList = []; // 임시 리스트 초기화
            listCount = 0; // 카운트값 초기화
            tempDate = null; // 임시 저장 날짜 초기화
          }
        });
        if (tempList !== []) {
          // 반복문 이후 임시 리스트가 남아 있는 경우
          convertedHistoryList.push(tempList);
        }
        commit("SET_CONVERTED_HISTORY_LIST", convertedHistoryList);
    },
    getPlanList({ getters, commit, dispatch }) {
      axios({
        url: spring.plan.getHistory(),
        method: "get",
        headers: {
          Authorization: `Bearer ${getters.token}`,
        },
      })
        .then((res) => {
          console.log("플랜 목록 수집 성공");
          console.log(res.data)
          commit("SET_PLAN_HISTORY_LIST", res.data);
          dispatch("convertPlanList");
        })
        .catch((err) => {
          console.error(err);
        });
    },
    createPlan({ getters, dispatch }, formData) {
      console.log(formData);
      axios({
        url: spring.plan.regist(),
        method: "post",
        data: formData,
        headers: {
          Authorization: `Bearer ${getters.token}`,
        },
      })
        .then((res) => {
          console.log(res);
          alert("플랜 작성에 성공하였습니다.");
          dispatch("getPlanList")
        })
        .catch((err) => {
          console.error(err);
          alert("플랜 작성에 실패하였습니다.");
        });
    },
    filterPlan({ getters, commit, dispatch }, formData) {
      console.log(formData);
      axios({
        url: spring.plan.filter(),
        method: "post",
        data: formData,
        headers: {
          Authorization: `Bearer ${getters.token}`,
        },
      })
        .then((res) => {
          console.log(res);
          alert("필터링 성공하였습니다!");
          commit("SET_PLAN_HISTORY_LIST", res.data);
          dispatch("convertPlanList");
        })
        .catch((err) => {
          console.error(err);
          alert("필터링 실패하였습니다");
        })
    }
  },
};
