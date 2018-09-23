
const Referrals = Vue.component('referral', {
    template: `<table class="table">
                 <thead>
                  <tr>
                   <th scope="col">#</th>
                   <th scope="col">Status</th>
                   <th scope="col">First Name</th>
                   <th scope="col">Last Name</th>
                  </tr>
                 </thead>
                 <tbody v-for="referral in referrals">
                    <tr v-for="referral in referrals">
                       <th scope="row">{{ referral.id }}</th>
                       <td>{{ referral.name }}</td>
                       <td>{{ referral.description }}</td>
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
