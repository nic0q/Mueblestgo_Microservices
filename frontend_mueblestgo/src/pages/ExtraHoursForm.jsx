import axios from 'axios';
import { useState } from 'react';

export default function JustificativeForm() {
  const [inputs, setInputs] = useState({});
  const [forbidden, setForbidden] = useState(false);

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    console.log(inputs)
    setInputs(values => ({...values, [name]: value}))
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post('/extra-hours/', inputs).then(res => {
      console.log(res);
    if(res.status === 403){
      setForbidden(true);
    }
    else{
      setForbidden(false);
    }
  });
  }

  return (
    <form onSubmit={handleSubmit} className="container">
      <h3>Ingresar Horas Extra</h3>
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
      <div className='form-group'>
        <label>Ingrese el número de horas:
        <input 
          type="text" 
          name="n_hours"
          className="form-control"
          value={inputs.n_hours || ""} 
          onChange={handleChange}
        />
        </label>
      </div>
      <br></br>
      <input type="submit" className="btn btn-primary"/>
      {forbidden && <div class="alert alert-danger" role="alert"> Usted no cuenta con privilegios de administrador para realizar esta acción </div>}
    </form>
  )
}
