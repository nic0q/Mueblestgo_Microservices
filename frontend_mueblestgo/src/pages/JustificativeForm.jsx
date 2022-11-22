import { useKeycloak } from '@react-keycloak/web'
import axios from 'axios';
import { useState } from 'react';
import jwt_decode from "jwt-decode";

export default function JustificativeForm() {
  const { keycloak } = useKeycloak()  
  const [inputs, setInputs] = useState({});
  const [forbidden, setForbidden] = useState(false);
  const [success, setSuccess] = useState(false);

  const decodeToken = (token) => {
    const decoded = jwt_decode(token);
    console.log(decoded)
    return decoded;
  }
  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    console.log(inputs)
    setInputs(values => ({...values, [name]: value}))
    console.log(keycloak.token)
  }
  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(keycloak.token)
    axios.post(`/justificatives`,inputs,
     {
      headers: { 'Authorization': `Bearer ${keycloak.token}` }
    }).then(res => {
      console.log(res.data);
      setSuccess(true);
    }).catch(err => 
      { // what now?     
      console.log(); 
      if(err.response.status === 403){
        setForbidden(true);
        setSuccess(false);
      }
      else{
        setForbidden(false);
        setSuccess(true);
      }
      });
  }
  return (
    <form onSubmit={handleSubmit} className="container">
      <h3>Ingresar justificativo</h3>
      {keycloak.authenticated&& <div>
        <h5>Good Afternoon {decodeToken(keycloak.token).preferred_username}</h5>
        <button class="btn btn-danger" onClick={() => keycloak.logout()}>Logout</button>
      </div> }
      <div className="form-group">
        <label>Ingrese el rut del empleado:
        <input 
          type="text" 
          name="rut_employee"
          className="form-control"
          value={inputs.rut_employee || ""} 
          placeholder='Sin puntos y con guión'
          onChange={handleChange}
        />
        </label>  
      </div>
      <div className='form-group'>
        <label>Ingrese la fecha:
        <input 
          type="date" 
          name="date"
          className="form-control"
          value={inputs.date || ""} 
          onChange={handleChange}
        />
        </label>
      </div>
      <br></br>
      <input type="submit" className="btn btn-primary"/>
      {forbidden ? <div>
        <br></br>
        <div class="alert alert-danger" role="alert"> Usted no cuenta con privilegios de administrador para realizar esta acción </div>
        </div> : ''}
        {success && <div>
        <br></br>
        <div class="alert alert-success" role="alert"> Jusitificativo ingresado correctamente </div>
        </div>}
    </form>
  )
}
