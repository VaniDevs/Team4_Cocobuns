
Vue.component('date-picker', {
  template: '<input/>',
  props: {
    'dateFormat': {
        type: String,
        default: 'yy-dd-mm'
    }
  },
  created() {
    $(this.$el).datetimepicker({
      dateFormat: this.dateFormat
    });
  },
  beforeDestroy() {
  }
});

const ReferralCreate = Vue.component('referral-create', {
    props: ['referral'],
    template: `
    <form v-on:submit.prevent="onSubmit">

      <div class="form-group">
        <label>Social-graphics</label>
        <div class="form-check" v-for="(option, index) in socialOptions">
              <input class="form-check-input" type="checkbox"
                value="" v-bind:id="option.value" v-bind:value="option.value" v-model="selectedSocialGroups">
              <label class="form-check-label" v-bind:for="option.value">
                {{ option.label }}
              </label>
        </div>
      </div>

      <div class="form-row">
        <div class="form-group col">
            <input type="text" class="form-control" placeholder="First name" v-model="referralRequest.client.firstName">
        </div>
        <div class="form-group col">
            <input type="text" class="form-control" placeholder="Last name" v-model="referralRequest.client.lastName">
        </div>
      </div>

      <div class="form-group">
        <label for="inputDOB">Date of Birth</label>
        <input type="text" class="form-control" id="inputDOB" placeholder="yy/mm/dd" v-model="referralRequest.client.dateOfBirth">
      </div>

      <div class="form-group">
        <label for="inputEmail">Email</label>
        <input type="email" class="form-control" id="inputEmail" placeholder="Email" v-model="referralRequest.client.email">
      </div>

      <div class="form-row">
        <div class="form-group col-md-6">
          <label for="inputHomePhone">Home Phone</label>
          <input type="text" class="form-control" id="inputHomePhone" placeholder="" v-model="referralRequest.client.phone">
        </div>
        <div class="form-group col-md-6">
          <label for="inputMobilePhone">Mobile Phone</label>
          <input type="text" class="form-control" id="inputMobilePhone" placeholder="">
        </div>
      </div>

      <div class="form-group">
        <label for="inputBabyDOB">Baby\'s Date of Birth</label>
        <input type="text" class="form-control" id="inputBabyDOB" placeholder="yy/mm/dd" v-model="referralRequest.client.babyDateOfBirth">
      </div>


      <label>Gear Requested</label>
      <div class="form-group">
        <div class="form-check form-check-inline" v-for="(option, index) in gearOptions">
          <input class="form-check-input" type="checkbox"
            value="" v-bind:id="option.value" v-bind:value="option.value">
          <label class="form-check-label" v-bind:for="option.value">
            {{ option.label }}
          </label>
        </div>
      </div>

      <button type="submit" class="btn btn-primary">Submit Referral</button>
    </form>
    `,
    data() {
      return {
        selectedSocialGroups: [],
        selectedRequestedGears: [],
        referralRequest: {
          caseStatus: 'new',
          client: {
            firstName: '', lastName: '',
            dateOfBirth: '', babyDateOfBirth: '',
            referredBy: {
                id: currentOrgId
            },
            socialgraphics: ''
          },
          openedBy: {
            id: currentAgentId,
            organization : {
                id: currentOrgId
            }
          }
        },
        socialOptions: [
          { label: "under 19", value: "UNDER_19" },
          { label: "unemployed", value: "UNEMPLOYED" },
          { label: "newcomer to Canada", value: "NEWCOMER" },
          { label: "child with special needs", value: "CHILD_SPECIAL_NEED" },
          { label: "homeless", value: "HOMELESS" }
        ],
        gearOptions: [
            { label: "crib", value: "CRIB" },
            { label: "bassinet", value: "BASSINET" },
            { label: "pack 'n play", value: "PACK_N_PLAY" },
            { label: "single stroller", value: "SINGLE_STROLLER" },
            { label: "double stroller", value: "DOUBLE STROLLER" },
            { label: "front carrier", value: "FRONT_CARRIER" },
            { label: "bouncy chair", value: "BOUNCY_CHAIR" },
            { label: "swing", value: "SWING" },
            { label: "exersaucer", value: "EXERSAUCER" },
            { label: "jolly jumper", value: "JOLLY_JUMPER" },
            { label: "bumbo", value: "BUMBO" },
            { label: "high chair", value: "HIGH_CHAIR" },
            { label: "bathtub", value: "BATHTUB" },
            { label: "diapers", value: "DIAPERS" },
            { label: "diaper bag", value: "DIAPER_BAG" },
            { label: "blankets", value: "BLANKETS" },
            { label: "crib bedding", value: "CRIB_BEDDING" },
            { label: "sleepsacks", value: "SLEEPSACKS" },
            { label: "clothing", value: "CLOTHING" },
            { label: "toys/books", value: "TOY_BOOKS" },
            { label: "nursing pillow", value: "NURSING_PILLOW" },
            { label: "safety gate", value: "SAFETY_GATE" },
            { label: "bottles", value: "BOTTLES" },
            { label: "feeding accessories", value: "FEEDING_ACCESSORIES" },
            { label: "monitor", value: "MONITOR" },
            { label: "safety gear", value: "SAFETY_GEAR" },
            { label: "breast pump", value: "BREAST_PUMP" }
        ]
      }
    },
    methods: {
        onSubmit() {
            this.referralRequest.client.sociographics = this.selectedSocialGroups.join(',');
            this.referralRequest.client.requestedGears = this.selectedRequestedGears.join(',');
            axios.post(`${apiBaseUrl}/case`, this.referralRequest)
              .then((response) => {
                console.log(response.data);
              });
        }
    }
});
