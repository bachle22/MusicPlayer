<?php
	require "connect.php";
	class Theloai{
		function Theloai($idtheloai,$idchude,$tentheloai,$hinhtheloai){
			$this->IdTheLoai = $idtheloai;
			$this->IdKeyChuDe = $idchude;
			$this->TenTheLoai = $tentheloai;
			$this->HinhTheLoai = $hinhtheloai;
		}
	}
	$arraytheloai = array();
	if(isset($_POST['idchude'])){
	$idchude= $_POST['idchude'];
	$query = "SELECT * FROM theloai WHERE FIND_IN_SET('$idchude',IdChuDe)";
	}
	$data= mysqli_query($con,$query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arraytheloai, new Theloai($row['IdTheLoai'],$row['IdChuDe'],$row['TenTheLoai'],$row['HinhTheLoai']));
	}
	echo json_encode($arraytheloai);
?>