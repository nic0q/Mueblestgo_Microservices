import './App.css';
import { Routes, Route } from "react-router-dom";
import Layout from './Layout';
import { Container } from 'react-bootstrap';
import Index from './components/Index';
import UploadFile from './components/UploadFile';
import JustificativeForm from './components/JustificativeForm';
import ExtraHoursForm from "./components/ExtraHoursForm"

function App() {
  return (
    <Layout>
      <Container>
        <Routes>
          <Route path='/' element={<Index/>} exact/>
          <Route path='/upload' element={<UploadFile/>} exact/>
          <Route path='/justificatives' element={<JustificativeForm/>} exact/>
          <Route path='/extra-hours' element={<ExtraHoursForm/>} exact/>
        </Routes>
      </Container>
    </Layout>
  );
}

export default App;
