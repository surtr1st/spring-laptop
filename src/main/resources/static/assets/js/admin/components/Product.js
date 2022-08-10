const REST_URL = "http://localhost:8080/rest"

export default {
    name: 'Product',
    data() {
      return {
        brands: [],
        types: [],
        products: [],
        src: '../images/products/',
        product: {
          brand: '',
          name: '',
          price: 0,
          discount: 0.0,
          quantity: 0,
          type: '',
          image: ''
        },
        typeName: ''
      }
    },
    methods: {
      onUpload() {
        const upload = document.getElementById('upload-photo')
        const img = document.getElementById('img')
        const [file] = upload.files
        if (file) {
          img.src = URL.createObjectURL(file)
        }
      },

      async getImageFile() {
        const upload = document.getElementById('upload-photo')
        const data = new FormData()
        data.append('file', upload.files[0])

        await axios.post(`${REST_URL}/upload/images/products`, data, {
          headers: {
            'Content-Type': 'multipart/form-data; boundary=none'
          }
        }).then(res => this.product.image = res.json().name)
      },

      onFetchProducts() {
        fetch(`${REST_URL}/products`, {
          method: 'GET',
          headers: {
            accept: 'application/json'
          }
        })
        .then(async res => {
          this.products = await res.json()
        })
        .catch(e => {
          console.log(`Products Error: ${e}`)
        })
      },

      onFetchBrands() {
        fetch(`${REST_URL}/brands`, {
          method: 'GET',
          headers: {
            accept: 'application/json'
          }
        })
        .then(async res => {
          this.brands = await res.json()
        })
        .catch(e => {
          console.log(`Brands Error: ${e}`)
        })
      },

      onFetchProductTypes() {
        fetch(`${REST_URL}/product-types`, {
          method: 'GET',
          headers: {
            accept: 'application/json'
          }
        })
        .then(async res => {
          this.types = await res.json()
          console.log(this.types)
        })
        .catch(e => {
          console.log(`Product-Types Error: ${e}`)
        })
      },

      onEdit(id) {
        fetch(`${REST_URL}/edit-product/${id}`)
          .then(async res => {
            this.product = await res.json()
          })
      },

      async onCreate() {
        const post = {
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }
        await this.getImageFile()
        await axios.post(`${REST_URL}/create-product`, this.product, post)
        await this.reRender()
        this.onReset()
      },

      async onUpdate() {
        const put = {
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        }
        await axios.put(`${REST_URL}/update-product`,  this.product, put)
        await this.reRender()
        this.onReset()
      },

      async onDelete(id) {
        await fetch(`${REST_URL}/delete-product/${id}`, {
          method: 'DELETE'
        })
        await this.reRender()
      },

      onReset() {
        this.product = {}
      },

      async reRender() {
        await Vue.nextTick()
        this.onFetchProducts()
      }
    },
    created() {
      this.onFetchBrands()
      this.onFetchProductTypes()
      this.onFetchProducts()
    },
    destroyed() {
      this.product = []
      this.brands = []
      this.types = []
    },
    template: /* html */
    `
        <div class="row col-xl-10 col-lg-10 p-5">
            <div class="col-lg-6">
              <form class="row" method="post" v-on:submit.prevent enctype="multipart/form-data">
                <div class="col-md-6 mb-3">
                  <label for="01" class="form-label">Brand</label>
                  <select v-model="product.brand" class="form-select">
                    <option
                      v-for="(brand, index) in brands"
                      :value="brand.bid"
                      :key="index">{{ brand.name }}</option>
                  </select>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="03" class="form-label">Type</label>
                  <select v-model="product.type" class="form-select" id="04">
                    <option 
                      v-for="(type, index) in types" 
                      :value="type.tid" 
                      :key="index">{{ type.name }}</option>
                  </select>
                </div>
                <div class="col-md-12 mb-3">
                  <label for="02" class="form-label">Name</label>
                  <input v-model="product.name" type="text" class="form-control" id="02">
                </div>
                <div class="col-md-4 mb-3">
                  <label for="05" class="form-label">Price</label>
                  <div class="input-group">
                    <span class="input-group-text" id="05">$</span>
                    <input v-model="product.price" type="text" class="form-control" id="05">
                  </div>
                </div>
                <div class="col-md-4 mb-3">
                  <label for="06" class="form-label">Discount</label>
                  <div class="input-group">
                    <span class="input-group-text" id="06">%</span>
                    <input v-model="product.discount" type="text" class="form-control" id="06">
                  </div>
                </div>
                <div class="col-md-4 mb-3">
                  <label for="05" class="form-label">Quantity</label>
                  <div class="input-group">
                    <span class="input-group-text" id="05">n</span>
                    <input v-model="product.quantity" type="text" class="form-control" id="05">
                  </div>
                </div>
                <div class="form-outline">
				    <label for="prodImg">Image</label>
					<div class="input-group mb-3">
						<input 
                            name="file"
                            type="file" 
                            v-model="product.image" 
                            @change="onUpload"
						    id="upload-photo"
                            class="form-control file"
                            accept="image/*">
                      <input
                            class="form-control bg-white text-center" id="prodImg"
						  	:value="product.image" readonly>
					</div>
				</div>
				<div class="form-outline text-center">
					<button @click="onCreate()" class="btn btn-lg btn-success m-2">Create</button>
					<button @click="onUpdate()" class="btn btn-lg btn-dark m-2">Update</button>
					<button @click="onReset()" class="btn btn-lg btn-info m-2">Reset</button>
				</div>
              </form>
            </div>
            <div class="col-lg-6">
                <div
				    class="bg-white border rounded d-flex justify-content-center align-items-center"
					style="height: 380px;">
					<img :src="src + product.image" class="img-fluid rounded" id="img" style="width: 60%;">
			    </div>
            </div>
            <div class="col-xl-12 col-md-6 my-1">
				<table class="table table-hover mb-0 bg-white rounded rounded-5">
					<thead class="bg-primary text-white text-center">
						<tr>
							<th colspan="2">Product</th>
							<th>Brand</th>
							<th>Name</th>
							<th>Price (with Discount)</th>
						    <th>Quantity</th>
						    <th>Type</th>
						    <th>Custom</th>
					    </tr>
					</thead>
			        <tbody class="text-center align-middle">
                        <tr v-for="(prod, index) in products" :key="index">
                            <td>
                                <a @click="onEdit(prod.id)" class="btn btn-outline-primary w-75">{{ prod.id }}</a>
                            </td>
                            <td>
                                <img :src="src + prod.image" class="img-fluid" style="max-width: 5rem;">
                            </td>
                            <td>{{ prod.brand }}</td>
                            <td>{{ prod.name }}</td>
                            <td>{{ prod.price }}</td>
                            <td>{{ prod.quantity }}</td>
                            <td>{{ getTypeName }}</td>
                            <td>
                                <a @click="onDelete(prod.id)" class="btn btn-outline-danger">Remove</a>
                            </td>
                       </tr> 
					</tbody>
				</table>
			</div>
		</div>
    </div>
    `,
};