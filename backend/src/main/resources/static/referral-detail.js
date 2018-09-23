
const ReferralDetail = Vue.component('referral-detail', {
    template: `
    <form v-on:submit.prevent="onSubmit">

      <div class="form-row">
        <div class="form-group col">
            <input type="text" class="form-control" placeholder="First name" v-model="referral.client.firstName">
        </div>
        <div class="form-group col">
            <input type="text" class="form-control" placeholder="Last name" v-model="referral.client.lastName">
        </div>
      </div>

      <div class="form-group">
        <label for="inputDOB">Date of Birth</label>
        <input type="text" class="form-control" id="inputDOB" placeholder="yy/mm/dd" v-model="referral.client.dateOfBirth">
      </div>

      <div class="form-group">
        <label for="inputEmail">Email</label>
        <input type="email" class="form-control" id="inputEmail" placeholder="Email" v-model="referral.client.email">
      </div>

      <div class="form-row">
        <div class="form-group col-md-6">
          <label for="inputHomePhone">Home Phone</label>
          <input type="text" class="form-control" id="inputHomePhone" placeholder="" v-model="referral.client.phoneNumber">
        </div>
        <div class="form-group col-md-6">
          <label for="inputMobilePhone">Mobile Phone</label>
          <input type="text" class="form-control" id="inputMobilePhone" placeholder="">
        </div>
      </div>

      <div class="form-group">
        <label for="inputBabyDOB">Baby\'s Date of Birth</label>
        <input type="text" class="form-control" id="inputBabyDOB" placeholder="yy/mm/dd" v-model="referral.client.babyDateOfBirth">
      </div>

      <button type="submit" class="btn btn-primary">Update</button>

      <button class="btn btn-info" data-toggle="modal" data-target="#notifyModal">Confirm Appointment</button>

      <hr/>

      <div class="card">
        <div class="card-header">Notes</div>
          <ul class="list-group list-group-flush">
            <li class="list-group-item" v-for="note in notes">{{ note.note }}</li>
          </ul>
        </div>

        <div class="input-group mb-3" style="margin-top:0.8rem">
          <input type="text" class="form-control" placeholder="Add additional note..." v-model="newNoteMessage">
          <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" v-on:click="addNote()">Add</button>
          </div>
        </div>

        <div class="modal fade" id="notifyModal" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Appointment Confirmation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form>

                  <label>Recipient:</label>
                  <div class="form-row">
                    <div class="form-group col">
                        <input type="text" class="form-control" readonly id="recipient-phone" :value="referral.client.phoneNumber">
                    </div>
                    <div class="form-group col">
                        <input type="text" class="form-control" readonly  id="recipient-email" :value="referral.client.email">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="message-text" class="col-form-label">Message:</label>
                    <textarea class="form-control" id="appointment-message-text" v-model="confirmationMessage"></textarea>
                  </div>
                </form>
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" v-on:click="sendConfirmationMessage()">Send message</button>
              </div>
            </div>
          </div>
        </div>

    </form>


    `,
    data() {
      return {
        loading: false,
        referral: {
            client: {}
        },
        notes: [],
        newNoteMessage: '',
        confirmationMessage: ''
      }
    },
    created() {
      this.fetchData();
      this.fetchNotes();
    },
    watch: {
        '$route': 'fetchData'
    },
    methods: {
        fetchData() {
            axios.get(`${apiBaseUrl}/case/${this.$route.params.id}`)
              .then((response) => {
                this.referral = response.data;
                this.confirmationMessage = `Hi ${this.referral.client.firstName} ${this.referral.client.lastName},
  Your request has been accepted by BabyGoRound representative.
  Please contact us at 604-888-9999, 9:30 AM - 2:30 PM Pacific Standard Time, Tuesday and Thursday.`
              });
        },
        fetchNotes() {
            axios.get(`${apiBaseUrl}/case/${this.$route.params.id}/notes`)
              .then((response) => {
                this.notes = response.data;
            });
        },
        onSubmit() {
            axios.post(`${apiBaseUrl}/case`, this.referral)
              .then((response) => {
              });
        },
        addNote() {
            axios.post(`${apiBaseUrl}/case/${this.$route.params.id}/note`, {
               note: this.newNoteMessage
              })
              .then((response) => {
                this.fetchNotes();
                this.newNoteMessage = null;
              });
        },
        sendConfirmationMessage() {
            axios.post(`${apiBaseUrl}/case/${this.$route.params.id}/approve`)
              .then((response) => {
                let appointmentDate = "2018-12-14";
                let clientNumber = this.referral.client.phoneNumber;
                axios.post(`${apiBaseUrl}/case/${this.$route.params.id}/schedule?date=${appointmentDate}&phoneNo=${clientNumber}`,
                    this.confirmationMessage, {headers: {"Content-Type": "text/plain"}})
                 .then((response2 => {
                    $("#notifyModal").modal('hide');
                }))
              });
        }
    }

});
