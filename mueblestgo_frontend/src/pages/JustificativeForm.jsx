import axios from 'axios';

export default function JustificativeForm() {
  // Here upload the info of justificative form axios post
  const submit = () =>{
    console.log('submit');
    
    
    

    
  }
  return (
    <div class="container ">
      <form class="">
        <div class="form-group">
          <label for="exampleInputEmail1">Rut Empleado</label>
          <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Sin puntos y con guión"/>
        </div>
        <div class="form-group">
          <label>Fecha</label>
          <input type="date" class="form-control" placeholder='Ingresar Fecha'/>
        </div>
        <button type="submit" class="btn btn-primary" onClick={submit}>Submit</button>
        <br/>
      </form>
    </div>
  );
}
