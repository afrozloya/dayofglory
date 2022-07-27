import './App.css';
import AnnotationMarker from './component/AnnotationMarker';
import Tasks from './component/Tasks';
import AppRoutes from './routes/Routes';
const IMAGE_URL = 'http://localhost:8080/uploads/Screenshot%20from%202022-07-15%2017-21-15.png';

function App() {
  return (
    <div className="App">
      <h1>Annotation Tasks!</h1>
      <AppRoutes />
    </div>
  );
}

export default App;
