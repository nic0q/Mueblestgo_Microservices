import { useKeycloak } from '@react-keycloak/web'

export default function PrivateRoute({ children }) {
  const { keycloak } = useKeycloak()
  return !keycloak.authenticated ? setTimeout(() => keycloak.login(), 1)  :  children;
}