
const ReferralCreate = Vue.component('referral-create', {
    props: ['referral'],
    template: `
    <form v-on:submit.prevent="onSubmit">

      <div class="form-row">
        <div class="form-group col">
            <input type="text" class="form-control" placeholder="First name" v-model="referralRequest.client.firstName">
        </div>
        <div class="form-group col">
            <input type="text" class="form-control" placeholder="Last name" v-model="referralRequest.client.lastName">
        </div>
      </div>

      <div class="form-group">
        <label>Social Group</label>
        <div class="form-check" v-for="(option, index) in socialOptions">
              <input class="form-check-input" type="checkbox"
                value="" v-bind:id="option.value" v-bind:value="option.value" v-model="selectedSocialGroups">
              <label class="form-check-label" v-bind:for="option.value">
                {{ option.label }}
              </label>
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

      <div class="form-group">
        <label for="inputHomePhone">Home Phone</label>
        <input type="text" class="form-control" id="inputHomePhone" placeholder="" v-model="referralRequest.client.phoneNumber">
      </div>


      <div class="form-group">
        <label for="inputBabyDOB">Baby\'s Date of Birth</label>
        <input type="text" class="form-control" id="inputBabyDOB" placeholder="yy/mm/dd" v-model="referralRequest.client.babyDateOfBirth">
      </div>

      <label>Gear Requested</label>
      <div class="row">
          <div class="col-sm-3 py-2" v-for='(g, gIndex) in groupedGearOptions'>
              <form class="form-inline" v-for='(item, index) in g'>
                  <div class="form-check">
                      <input class="form-check-input" type="checkbox" v-model="selectedRequestedGears"
                        v-bind:id="item.value" v-bind:value="item.label">
                      <label class="form-check-label" v-bind:for="item.value">
                       {{ item.label }}
                      </label>
                  </div>
              </form>
          </div>
      </div>

      <div class="clearfix">
       <button type="submit" class="btn btn-primary float-right " v-bind:disabled="isLoading"><i v-show="isLoading" class="fa fa-spinner fa-spin"></i> Submit Referral</button>
      </div>
      </form>
    `,
    data() {
      return {
        isLoading: false,
        selectedSocialGroups: [],
        selectedRequestedGears: [],
        referralRequest: {
          caseStatus: 'NEW',
          client: {
            firstName: '', lastName: '',
            dateOfBirth: '', babyDateOfBirth: ''
          },
          openedBy: {
            id: currentAgentId
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
        ],
        groupedGearOptions: []
      }
    },
    created() {
      $('#introMessage').text("Referral Form - Agency");


      let size = 7;
      let newArr = [];
      for (var i=0; i < this.gearOptions.length; i+=size) {
        newArr.push(this.gearOptions.slice(i, i+size));
      }
      this.groupedGearOptions = newArr;
    },
    methods: {
        onSubmit() {
            this.referralRequest.client.sociographics = this.selectedSocialGroups.join(', ');
            this.isLoading = true;
            axios.post(`${apiBaseUrl}/case`, this.referralRequest)
              .then((response) => {

                this.isLoading = false;
                router.push('referrals');

                let newCase = response.data;
                let note = "Referral request did not specify any gears"
                if (this.selectedRequestedGears.length) {
                    note = 'The following gears have been requested: ' + this.selectedRequestedGears.join(', ')
                }
                axios.post(`${apiBaseUrl}/case/${newCase.id}/note`, {
                    note
                });

              });
        },
    }
});
