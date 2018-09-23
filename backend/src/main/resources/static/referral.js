
Vue.component('case-status', {
  props: ['status'],
  template: `
    <span class="badge" v-bind:class="computeBadgeColor">{{status}}</span>
  `,
  computed: {
      computeBadgeColor() {
        return {
            "badge-info": this.status == 'NEW',
            "badge-danger": this.status == 'APPROVED',
            "badge-warning": this.status == 'SCHEDULED'
        }
      }
    }
});

const Referrals = Vue.component('referral', {
    template: `
        <table class="table referral-table table-striped table-hover">
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
                       <td><case-status v-bind:status="referral.caseStatus"></case-status></td>
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
      $('#introMessage').text("Most Recent Referrals");
      this.fetchData();
    },
    watch: {
        '$route': 'fetchData'
    },
    filters: {
      moment(date) {
        return moment(date).format('MMM Do YY, h:mm a');
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
