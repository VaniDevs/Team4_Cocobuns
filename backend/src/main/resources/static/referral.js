
const Referrals = Vue.component('referral', {
    template: `<table class="table referral-table">
                 <thead>
                  <tr>
                   <th scope="col">#</th>
                   <th scope="col">Date</th>
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
                       <th scope="row">{{ referral.id }}</th>
                       <td>{{ referral.createDateTime | moment }}</th>
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
    filters: {
      moment: function (date) {
        return moment(date).format('MMMM Do YYYY, h:mm a');
      }
    },
    methods: {
        fetchData() {
            axios.get(`${apiBaseUrl}/cases`)
              .then((response) => {
                this.referrals = response.data;
              });
        },
        openDetail(id) {
            router.push(`referral-detail/${id}`);
        }
    }
});
