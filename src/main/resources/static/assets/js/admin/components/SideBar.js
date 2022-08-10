export default {
    name: 'SideBar',
    template:
    /* html */
    `
        <div class="d-flex flex-column flex-shrink-0 p-3 text-white bg-dark" style="min-height: 100vh;">
            <router-link to="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
              <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
              <span class="fs-4">Administrator</span>
            </router-link>
            <hr>
            <ul class="nav nav-pills flex-column mb-auto">
              <li class="list-group mb-2">
                <router-link to="/dashboard" class="list-group-item list-group-item-action bg-dark text-white">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Dashboard
                </router-link> 
              </li>
              <li class="list-group mb-2">
                <router-link to="/products" class="list-group-item list-group-item-action bg-dark text-white">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Products
                </router-link> 
              </li>
              <li class="list-group mb-2">
                <router-link to="/accounts" class="list-group-item list-group-item-action bg-dark text-white">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Accounts
                </router-link> 
              </li>
              <li class="list-group mb-2">
                <a href="/index" class="list-group-item list-group-item-action bg-dark text-white">
                    <svg class="bi me-2" width="16" height="16"></svg>
                    Homepage
                </a> 
              </li>
            </ul>
            <hr>
        </div>
    `
};