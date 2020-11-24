<template>
  <div class="home">
    <div class="row">
      <div id="rowbuildinglist" style="height: 300px; overflow:scroll;">
        <table class="table table-hover table-sm">
          <tbody>
            <tr
              v-for="(apt, index) in aptlist"
              :key="index"
              @click="mapBarMove(apt)"
            >
              <td>{{ index + 1 }}</td>
              <td>{{ apt.dong }}</td>
              <td>{{ apt.aptName }}</td>
              <td><button @click="detailInfo(apt.no)">상세정보</button></td>

              <!-- <td>{{apt.regidate}}</td> -->
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="row">
      <div id="detaildiv">
        <table class="table table-sm">
          <tbody id="detailbody">
            <tr>
              <td style="width:20%;">이름</td>
              <td>{{ detailHouse.aptName }}</td>
            </tr>
            <tr>
              <td>주소</td>
              <td>
                {{ detailHouse.dong }} {{ detailHouse.jibun }} 번지
                {{ detailHouse.floor }} 층
              </td>
            </tr>
            <tr>
              <td>거래금액</td>
              <td>{{ detailHouse.dealAmount }}</td>
            </tr>
            <tr>
              <td>건축연도</td>
              <td>{{ detailHouse.buildYear }}</td>
            </tr>
            <tr>
              <td>전용면적</td>
              <td>{{ detailHouse.area }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <q-list bordered class="rounded-borders">
      <q-expansion-item
        v-for="(apt, index) in aptlist"
        :key="index"
        expand-separator
        icon="perm_identity"
        label="Account settings"
        caption="아파트 리스트다"
      >
        <q-card>
          <q-card-section>
            <ul>
              <li>이 름 : {{ detailHouse.aptName }}</li>
              <li>
                주 소 :
                {{ detailHouse.dong }}
                {{ detailHouse.jibun }}번지 {{ detailHouse.floor }} 층
              </li>
              <li>거래금액 : {{ detailHouse.dealAmount }}</li>
              <li>건축연도 : {{ detailHouse.buildYear }}</li>
              <li>전용면적 : {{ detailHouse.area }}</li>
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
