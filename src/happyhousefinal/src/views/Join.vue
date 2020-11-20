<template>
  <div>
    <b-form-group
      id="userid"
      label="아이디"
      label-for="userid"
      valid-feedback="Thank you!"
      :invalid-feedback="idinvalidFeedback"
      :state="stateid"
    >
      <b-form-input id="userid" v-model="userid" :state="stateid" trim></b-form-input>
    </b-form-group>

    <b-form-group
      id="username"
      label="이름"
      label-for="username"
      valid-feedback="Thank you!"
      :invalid-feedback="nameinvalidFeedback"
      :state="statename"
    >
      <b-form-input id="username" v-model="username" :state="statename" trim></b-form-input>
    </b-form-group>

    <b-form-group
      id="userpwd"
      label="비밀번호"
      label-for="userpwd"
      valid-feedback="Thank you!"
      :invalid-feedback="pwdinvalidFeedback"
      :state="statepwd"
    >
      <b-form-input type="password" id="userpwd" v-model="userpwd" :state="statepwd" trim></b-form-input>
    </b-form-group>

    <b-form-group
      id="confirmpwd"
      label="비밀번호 확인"
      label-for="confirmpwd"
      valid-feedback="Thank you!"
      :invalid-feedback="confirmpwdinvalidFeedback"
      :state="statecpwd"
    >
      <b-form-input
        type="password"
        id="confirmpwd"
        v-model="confirmpwd"
        :state="statecpwd"
        trim
      ></b-form-input>
    </b-form-group>

    <b-form-group
      id="useremail"
      label="이메일"
      label-for="useremail"
      valid-feedback="Thank you!"
      :invalid-feedback="emailinvalidFeedback"
      :state="stateemail"
    >
      <b-form-input type="email" id="useremail" v-model="useremail" :state="stateemail" trim></b-form-input>
    </b-form-group>

    <b-form-group
      id="useraddress"
      label="주소"
      label-for="useraddress"
      valid-feedback="Thank you!"
      :invalid-feedback="addrinvalidFeedback"
      :state="stateaddr"
    >
      <b-form-input
        type="address"
        id="useraddress"
        v-model="useraddress"
        :state="stateaddr"
        trim
      ></b-form-input>
    </b-form-group>
  </div>
</template>

<script>
export default {
  computed: {
    stateid() {
      return this.userid.length >= 4;
    },
    statename() {
      return this.username.length >= 4;
    },
    statepwd() {
      return this.userpwd.length >= 4;
    },
    statecpwd() {
      return this.userpwd == this.confirmpwd;
    },
    stateemail() {
      return this.useremail.length >= 4;
    },
    stateaddr() {
      return this.useraddress.length >= 4;
    },
    idinvalidFeedback() {
      if (this.userid.length > 0) {
        return 'Enter at least 4 characters.';
      }
      return 'Please enter something.';
    },
    nameinvalidFeedback() {
      if (this.username.length > 0) {
        return 'Enter at least 4 characters.';
      }
      return 'Please enter something.';
    },
    pwdinvalidFeedback() {
      if (this.userpwd.length > 0) {
        return 'Enter at least 4 characters.';
      }
      return 'Please enter something.';
    },
    confirmpwdinvalidFeedback() {
      if (this.userpwd !== this.confirmpwd) {
        return '비밀번호와 다릅니다.';
      }
      return 'Please enter something.';
    },
    emailinvalidFeedback() {
      if (this.useremail.length > 0) {
        return 'Enter at least 4 characters.';
      }
      return 'Please enter something.';
    },
    addrinvalidFeedback() {
      if (this.useraddress.length > 0) {
        return 'Enter at least 4 characters.';
      }
      return 'Please enter something.';
    },
  },
  data() {
    return {
      check: 0,
      userid: '',
      username: '',
      userpwd: '',
      confirmpwd: '',
      useremail: '',
      useraddress: '',
    };
  },
};
</script>
