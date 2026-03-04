import { BrowserRouter, Routes, Route } from 'react-router-dom';  // ✅ FIXED
import './App.css';
import Home from './management/Home';
import Dashboard from './management/Dashboard';
import Suppliers from './management/Supplier';
import Products from './management/Products';
import Orders from './management/Orders';
import Customers from './management/Customers';
import AddSupplierForm from './Add Forms/AddSupplierForm';
import ProductForm from './Add Forms/ProductForm';
import OrderForm from './Add Forms/OrderForm';
import CustomerForm from './Add Forms/CustomerForm';

function App() {
  return (
    <BrowserRouter>  
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          
<Route path="/suppliers" element={<Suppliers />} />
<Route path="/products" element={<Products />} />
<Route path="/orders" element={<Orders />} />
<Route path="/customers" element={<Customers />} />
  <Route path="/dashboard" element={<Dashboard />} />
<Route path="/add-supplier" element={<AddSupplierForm />} />
<Route path="/add-product" element={<ProductForm />} />
  <Route path="/add-order" element={<OrderForm />} />
  <Route path="/add-customer" element={<CustomerForm/>}/>


        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
