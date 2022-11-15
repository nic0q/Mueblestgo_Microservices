import { useKeycloak } from '@react-keycloak/web'
import { Link } from 'react-router-dom'
import axios from 'axios'
import jwt_decode from "jwt-decode";
export default function Index() {
// or Object destructuring
const { keycloak } = useKeycloak()

// Here you can access all of keycloak methods and variables.
// See https://www.keycloak.org/docs/latest/securing_apps/index.html#javascript-adapter-reference
const decodeToken = (token) => {
  const decoded = jwt_decode(token);
  return decoded;
}

const isAdmin = (token) => {
  const decoded = decodeToken(token);
  return decoded.realm_access.roles.includes('ADMIN');
}
function bearerAuth(token) {
  return `Bearer ${token}`
}
function getMovie(token) {
  return axios.get(`/justificatives`, {
    headers: { 'Authorization': bearerAuth(token) }
  }).then(response => {
    console.log(response.data);
  })
}
return (
  <div>
    <Link to='/upload'><h1>Subir Archivo</h1></Link>
    {
      keycloak.authenticated ?
      <div>
        <h4>
          El usuario autenticado es {decodeToken(keycloak.token).preferred_username}
        </h4>
        {isAdmin(keycloak.token) ? <h4>El usuario es administrador</h4> : <h4>El usuario no es administrador</h4>}
        <button  onClick={() => keycloak.logout()}>Logout</button>
      </div>
      :
      <button  onClick={() => keycloak.login()}>Login</button>
    }<button onClick={()=>getMovie(keycloak.token)}>GET WEAS</button>
  </div>)
}