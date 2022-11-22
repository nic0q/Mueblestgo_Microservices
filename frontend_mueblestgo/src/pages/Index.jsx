import { Link } from "react-router-dom";


export default function Index() {
  return <div>
    <h1>Index</h1>
    <ul>
      <li>
        <Link to="/upload">Subir archivo</Link>
      </li>
      <li>
        <Link to="/extra-hours">Ingresar horas extra</Link>
      </li>
      <li>
        <Link to="/justificatives">Ingresar Justificativos</Link>
      </li>
      <li>
        <Link to="/planilla">Ver planilla</Link>
      </li> 
    </ul>
  </div>
}