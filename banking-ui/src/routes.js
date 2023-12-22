import AddUser from './containers/AddUser';
import ListUsers from './containers/ListUser';

export const routes = [
  {
    path: '/',
    title: 'List User',
    component: <ListUsers />,
  },
  {
    path: '/addUser',
    title: 'Add User',
    component: <AddUser />,
  },
];
