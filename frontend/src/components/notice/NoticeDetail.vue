<template>
  <div>
    <v-container>
      <v-row>
        <v-col class="d-flex mb-6">
          <v-btn depressed color="yellow" @click="moveToList"> 뒤로가기 </v-btn>
        </v-col>
      </v-row>
      <v-row class="mb-6">
        <v-col
          ><v-card>
            {{ notice.title }}
            <div>작성일 {{ notice.noticeDate }}</div>
            <div>조회수 {{ notice.hits }}</div></v-card
          >
        </v-col>
      </v-row>
      <v-row>
        <v-col
          ><v-card class="" height="300px">{{ notice.content }}</v-card></v-col
        >
      </v-row>
      <v-row>
        <v-col class="d-flex justify-end mb-6">
          <v-btn v-if="!notice.fixationFlag" depressed color="primary" @click="fixedToggle"> 상단고정 설정 </v-btn>
          <v-btn v-else depressed color="primary" @click="fixedToggle"> 상단고정 해제 </v-btn>
          <v-btn depressed color="red" @click="moveToUpdate"> 수정 </v-btn>
          <v-btn depressed color="blue" @click="NoticeDelete"> 삭제 </v-btn>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import Swal from "sweetalert2";

export default {
  computed: {
    ...mapState("Notices", ["notice"]),
  },
  data() {
    return {
      idx: this.$route.params.id,
    };
  },
  created() {
    // actions로 noticeId값 보내기
    const pathName = new URL(document.location).pathname.split("/");
    const noticeId = pathName[pathName.length - 1];
    this.$store.dispatch("Notices/getNotice", noticeId);
  },
  methods: {
    moveToList() {
      this.$router.push({
        name: "NoticeList",
      });
    },
    fixedToggle() {
      this.notice.fixationFlag = !this.notice.fixationFlag;
      this.$store.dispatch("Notices/modifyNotice", this.notice);
      Swal.fire({
        icon: "success",
        title: "공지사항 상단고정 내역이 변경되었습니다!",
        showConfirmButton: false,
        timer: 1000,
      });
      this.$router.push("/notice");
    },
    moveToUpdate() {
      this.$router.push({
        path: `/notice/update/${this.notice.noticeId}`,
      });
    },
    NoticeDelete() {
      this.$store.dispatch("Notices/deleteNotice", this.notice.noticeId);
      Swal.fire({
        icon: "success",
        title: "공지사항 삭제가 완료되었습니다!",
        showConfirmButton: false,
        timer: 1000,
      });
      this.$router.push("/notice");
    },
  },
};
</script>

<style></style>
