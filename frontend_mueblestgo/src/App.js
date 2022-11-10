import './App.css';
import { Routes, Route } from "react-router-dom";
import Layout from './Layout';
import { Container } from 'react-bootstrap';
import Index from './pages/Index';
import UploadFile from './pages/UploadFile';
import JustificativeForm from './pages/JustificativeForm';
import ExtraHoursForm from "./pages/ExtraHoursForm"
import Planilla from "./pages/Planilla"

function App() {
  return (
    <Layout>
      <Container>
        <Routes>
          <Route path='/' element={<Index/>} exact/>
          <Route path='/upload' element={<UploadFile/>} exact/>
          <Route path='/justificatives' element={<JustificativeForm/>} exact/>
          <Route path='/extra-hours' element={<ExtraHoursForm/>} exact/>
          <Route path='/planilla' element={<Planilla/>} exact/>
        </Routes>
      </Container>
    </Layout>
  );
}

export default App;
