import axios from 'axios';
import { useState } from 'react';

export default function JustificativeForm() {
  const [inputs, setInputs] = useState({});

  const handleChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    console.log(inputs)
    setInputs(values => ({...values, [name]: value}))
  }

  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post('http://localhost:8080/extra-hours', inputs).then(res => {
      console.log(res);
    });
  }

  return (
    <form onSubmit={handleSubmit} className="container">
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
    </form>
  )
}
