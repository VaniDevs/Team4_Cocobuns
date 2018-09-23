
const Referrals = Vue.component('referral', {
    template: `<table class="table">
                 <thead>
                  <tr>
                   <th scope="col">Created</th>
                   <th scope="col">Status</th>
                   <th scope="col">First Name</th>
                   <th scope="col">Last Name</th>
                  </tr>
                 </thead>
                 <tbody>
                    <tr v-for="referral in referrals">
                       <td>{{ referral.createDateTime }}</td>
                       <td>{{ referral.caseStatus }}</td>
                       <td>{{ referral.client.firstName }}</td>
                       <td>{{ referral.client.lastName }}</td>
                    </tr>
                 </tbody>
                 </table>`,
    data() {
      return {
        loading: false,
        post: null,
        error: null,
        referrals: []
      }
    },
    created() {
      this.fetchData();
    },
    watch: {
        '$route': 'fetchData'
    },
    methods: {
        fetchData() {
            axios.get(`${apiBaseUrl}/cases`)
              .then((response) => {
                this.referrals = response.data;
              });
        }
    }
});
