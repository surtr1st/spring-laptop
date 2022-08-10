const REST_URL = 'http://localhost:8080/rest'

export default {
    name: 'Dashboard',
    data() {
        return {
            totalProduct: [],
            totalUser: [],
            tables: []
        }
    },
    async created() {
        await fetch(`${REST_URL}/total-product`)
            .then(async res => this.totalProduct = await res.json())
            .catch(e => console.log(e))
        await fetch(`${REST_URL}/total-user`)
            .then(async res => this.totalUser = await res.json())
            .catch(e => console.log(e))
        await fetch(`${REST_URL}/tables`)
            .then(async res => this.tables = await res.json())
            .catch(e => console.log(e))
    },
    template: /*html*/
    `
    <div class="row col-xl-10 col-lg-10 p-5">
    <div class="col-xl-12 col-md-6 mb-4">
        <div class="card border-left-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="h3 font-weight-bold text-primary text-uppercase mb-1">
                            Total Product
                        </div>
                        <div class="display-1 text-end mb-0 font-weight-bold text-gray-800">{{ totalProduct }}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-12 col-md-6 mb-4">
        <div class="card border-left-success shadow h-100 py-2">
            <div class="card-body">
                <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                        <div class="h3 font-weight-bold text-success text-uppercase mb-1">
                            Total User
                        </div>
                        <div class="display-1 text-end mb-0 font-weight-bold text-gray-800">{{ totalUser }}</div>
                    </div>
                    <div class="col-auto">
                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-xl-12 col-md-6 mb-4">
  <div class="card border-left-warning shadow h-100 py-2">
    <div class="card-body">
      <div class="row no-gutters align-items-center">
        <div class="col mr-2">
          <div class="h3 font-weight-bold text-warning text-uppercase mb-1">
            Database's Table</div>
          <div class="h5 font-weight-bold text-gray-800">
            <div class="d-flex justify-content-center align-items-center text-white">
                <a v-for="(table, index) in tables" :key="index" class="btn btn-success mx-1 mt-3">{{ table }}</a>
            </div>
          </div>
        </div>
        <div class="col-auto">
          <i class="fas fa-comments fa-2x text-gray-300"></i>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
    `
}