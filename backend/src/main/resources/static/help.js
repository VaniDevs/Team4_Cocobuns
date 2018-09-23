
const Help = Vue.component('help', {
    template: `<div class="team-members">Dwi Faulus &bull; Jim Quitevis &bull; Petr Hecko &bull; Hubert Pan</div>`,
    created() {
      $('#introMessage').text("Team Coco Buns");
    }
});