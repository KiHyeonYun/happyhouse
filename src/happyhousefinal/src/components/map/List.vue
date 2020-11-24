<template>
  <div class="home">
    <q-list bordered class="rounded-borders">
      <q-expansion-item
        v-for="(apt, index) in aptlist"
        :key="index"
        expand-separator
        icon="perm_identity"
        :label="apt.aptName"
        :caption="apt.dealAmount + manwon"
        @click="mapBarMove(apt)"
      >
        <q-card>
          <q-card-section>
            <ul>
              <li>이 름 : {{ apt.aptName }}</li>
              <li>
                주 소 :
                {{ apt.dong }}
                {{ apt.jibun }}번지 {{ apt.floor }} 층
              </li>
              <li>거래금액 : {{ apt.dealAmount + manwon }}</li>
              <li>건축연도 : {{ apt.buildYear }}년</li>
              <li>전용면적 : {{ apt.area }}m<sup>3</sup></li>
            </ul>
          </q-card-section>
        </q-card>
      </q-expansion-item>
    </q-list>

    <!-- {{aptlist}} -->
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "List",

  data() {
    return {
      manwon: "만원",
      detailHouse: {}
    };
  },

  props: {
    aptlist: null
  },
  methods: {
    mapBarMove: function(apt) {
      this.$emit("select-apt", apt);
    },
    detailInfo: function(no) {
      axios
        .get(process.env.VUE_APP_SERVER_URL + "/map/detail/" + no)
        .then(response => {
          console.log(response);
          this.detailHouse = response.data;
        })
        .catch(error => {
          console.log(error);
        });
    }
  },
  mounted() {}
};
</script>

<style scoped></style>
