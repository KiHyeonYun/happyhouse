export function someGetter(state) {
    getters: {
        getAccessToken(state) {
          return state.accessToken;
        },
        getUserId(state) {
          return state.userId;
        },
        getUserName(state) {
          return state.userName;
        }
      }
}
