<template>
  <div>
    <v-container class="">
      <v-form ref="form" v-model="valid" lazy-validation>
        <v-text-field v-model="title" :counter="10" :rules="titleRules" label="제목" required></v-text-field>
        <v-textarea v-model="content" :counter="300" label="내용" :rules="contentRules" required></v-textarea>
        <div class="d-flex justify-end mb-6">
          <v-btn :disabled="!valid" color="success" class="d-flex justify-end" @click="[validate(), writeNotice()]">
            등록
          </v-btn>
        </div>
      </v-form>
    </v-container>
  </div>
</template>

<script>
import Swal from "sweetalert2";

export default {
  data() {
    return {
      valid: true,
      title: "",
      titleRules: [
        (v) => !!v || "제목을 입력하세요",
        (v) => (v && v.length <= 10) || "제목은 10글자 이하로 입력하세요",
      ],
      content: "",
      contentRules: [(v) => !!v || "내용을 입력하세요", (v) => (v && v.length <= 300) || "내용은 최대 200글자입니다."],
    };
  },

  methods: {
    validate() {
      this.$refs.form.validate();
    },
    writeNotice() {
      const newNotice = {
        title: this.title,
        content: this.content,
      };
      this.$store.dispatch("Notices/writeNotice", newNotice);
      Swal.fire({
        icon: "success",
        title: "공지사항 작성이 완료되었습니다!",
        showConfirmButton: false,
        timer: 1000,
      });
      this.$router.push("/notice");
    },
  },
};
</script>

<style></style>
