import { useKeycloak } from '@react-keycloak/web'
import { Link } from 'react-router-dom'
import axios from 'axios'
import jwt_decode from "jwt-decode";

export default function IndexExample() {
  const { keycloak } = useKeycloak()

  const decodeToken = (token) => {
    const decoded = jwt_decode(token);
    console.log(decoded)
    return decoded;
  }
  const getTodo = async (token) => {
    return axios.get(`/justificatives`, {
      headers: { 'Authorization': `Bearer ${token}` }
    }).then(response => {
      console.log(response.data);
    })
  }
  const get = async (id, token) => {
    return axios.get(`/justificatives/${id}`, {
      headers: { 'Authorization': `Bearer ${token}` }
    }).then(response => {
      console.log(response.data);
    })
  }
  const post = async (token) => {
    return axios.post(`/justificatives`,{
      "rut_employee": "20457671-9",
      "date": "2022-02-02"
    },
     {
      headers: { 'Authorization': `Bearer ${token}` }
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
          <button  onClick={() => keycloak.logout()}>Logout</button>
        </div>
        :
        <button  onClick={() => keycloak.login()}>Login</button>
      }
      <button onClick={()=>get(1,keycloak.token)}>GET 1</button>
      <button onClick={()=>getTodo(keycloak.token)}>GET TODO</button>
      <button onClick={()=>post(keycloak.token)}>POST</button>
    </div>
    )
}