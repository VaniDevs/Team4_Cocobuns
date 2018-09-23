
const Referrals = Vue.component('referral', {
    template: `<table class="table">
                 <thead>
                  <tr>
                   <th scope="col">Created</th>
                   <th scope="col">Status</th>
                   <th scope="col">First Name</th>
                   <th scope="col">Last Name</th>
                   <th scope="col">Email</th>
                   <th scope="col">Phone</th>
                   <th scope="col">Agent</th>
                  </tr>
                 </thead>
                 <tbody>
                    <tr v-for="referral in referrals" v-on:click="openDetail(referral.id)">
                       <th scope="row">{{ referral.createDateTime }}</th>
                       <td>{{ referral.caseStatus }}</td>
                       <td>{{ referral.client.firstName }}</td>
                       <td>{{ referral.client.lastName }}</td>
                       <td>{{ referral.client.email }}</td>
                       <td>{{ referral.client.phoneNumber }}</td>
                       <td>{{ referral.openedBy.firstName + ' ' + referral.openedBy.lastName }}</td>
                    </tr>
                 </tbody>
                 </table>`,
    data() {
      return {
        loading: false,
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

                console.log(response.data);
                this.referrals = response.data;

              });
        },
        openDetail(id) {
            router.push(`referral-detail/${id}`);
        }
    }
});
